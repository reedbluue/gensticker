package dev.ioliver.genstickerapi.service.StickerService;

import org.springframework.web.multipart.MultipartFile;

public record StickerSchemaDto(String descricao, MultipartFile imagem) {}
