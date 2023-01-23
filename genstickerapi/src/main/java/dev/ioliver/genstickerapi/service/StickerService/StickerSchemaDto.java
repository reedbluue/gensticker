package dev.ioliver.genstickerapi.service.StickerService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record StickerSchemaDto(@Length(max = 13) @NotBlank String descricao, @NotNull MultipartFile imagem) {}
