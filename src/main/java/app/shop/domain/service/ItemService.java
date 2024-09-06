package app.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import app.shop.core.BaseService;
import app.shop.domain.entity.Item;
import app.shop.domain.repository.ItemRepository;

@RequiredArgsConstructor
@Service
public class ItemService extends BaseService {
    private final ItemRepository itemRepository;

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }
}
