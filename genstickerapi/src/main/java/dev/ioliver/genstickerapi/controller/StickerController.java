package dev.ioliver.genstickerapi.controller;

import dev.ioliver.genstickerapi.service.StickerService.StickerSchemaDto;
import dev.ioliver.genstickerapi.service.StickerService.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("generate")
public class StickerController {

  @Autowired
  StickerService stickerService;

  @PostMapping
  public ResponseEntity<byte[]> generateSticker(@RequestParam String descricao, @RequestParam MultipartFile imagem) throws IOException {
    BufferedImage bufferedImage = stickerService.generate(new StickerSchemaDto(descricao, imagem));
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", os);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(os.toByteArray());
  }

}
