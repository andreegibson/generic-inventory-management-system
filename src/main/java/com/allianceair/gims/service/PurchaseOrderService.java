package com.allianceair.gims.service;

import com.allianceair.gims.model.InventoryItem;
import com.allianceair.gims.model.PurchaseOrder;
import com.allianceair.gims.model.PurchaseOrderItem;
import com.allianceair.gims.model.PurchaseOrderStatus;
import com.allianceair.gims.repository.InventoryItemRepository;
import com.allianceair.gims.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseOrderService {
    @Autowired
    private final PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private final ItemService itemService;

    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setDateAdded(LocalDateTime.now());
        purchaseOrder.setLastModified(LocalDateTime.now());
        purchaseOrder.setStatus(PurchaseOrderStatus.Open);

        purchaseOrder.getItems().forEach(item -> {
            item.setDateAdded(LocalDateTime.now());
            item.setLastModified(LocalDateTime.now());
        });

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> findAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder findById(String purchaseOrderId) {
        return purchaseOrderRepository.findById(purchaseOrderId).get();
    }

    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        purchaseOrder.setLastModified(LocalDateTime.now());
        int totalItems = 0;
        int totalReceived = 0;

        for (PurchaseOrderItem item: purchaseOrder.getItems()) {
            item.setLastModified(LocalDateTime.now());

            totalReceived += Optional.ofNullable(item.getQuantityReceived()).orElseGet(() -> 0);
            totalItems += item.getQuantity();
        }

        if(!PurchaseOrderStatus.Cancelled.equals(purchaseOrder.getStatus()) && totalItems == totalReceived) {
            purchaseOrder.setStatus(PurchaseOrderStatus.Received);
        } else if(!PurchaseOrderStatus.Cancelled.equals(purchaseOrder.getStatus()) && totalReceived > 0) {
            purchaseOrder.setStatus(PurchaseOrderStatus.PartiallyReceived);
        }

        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder receive(PurchaseOrder purchaseOrder) {
        //NOTE: This is some ugly nesting but trying this new stuff out
        purchaseOrderRepository.findById(purchaseOrder.getId()).ifPresentOrElse(savedPurchaseOrder -> {
            for (int i = 0; i < purchaseOrder.getItems().size(); i++) {
                int quantityToRecieve = 0;

                if(purchaseOrder.getItems().get(i).getQuantityReceived() != null)
                    quantityToRecieve+= purchaseOrder.getItems().get(i).getQuantityReceived();

                if(savedPurchaseOrder.getItems().get(i).getQuantityReceived() != null)
                    quantityToRecieve -= savedPurchaseOrder.getItems().get(i).getQuantityReceived();

                receiveInventory(quantityToRecieve, purchaseOrder.getId(), purchaseOrder.getItems().get(i));
            }
        },
                () -> {throw new IllegalArgumentException();});

        return purchaseOrderRepository.save(purchaseOrder);
    }

    private void receiveInventory(int quantity, String purchaseOrderId, PurchaseOrderItem purchaseOrderItem) {
        log.debug("receiving {}", quantity);
        for (int i = 0; i < quantity; i++) {
            itemService.addItem(InventoryItem.builder()
                            .brand(purchaseOrderItem.getBrand())
                            .purchaseOrderId(purchaseOrderId)
                            .category(purchaseOrderItem.getCategory())
                            .description(purchaseOrderItem.getDescription())
                            .imageUrl(purchaseOrderItem.getImageUrl())
                            .name(purchaseOrderItem.getName())
                            .type(purchaseOrderItem.getType())
                    .build());
        }
    }
}
