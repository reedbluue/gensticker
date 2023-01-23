package dev.ioliver.genstickerapi.service.StickerService;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class StickerService {

  public BufferedImage generate(StickerSchemaDto sticker) throws IOException {

    BufferedImage oldBufferedImage = ImageIO.read(sticker.imagem().getInputStream());

    BufferedImage newBufferedImage = new BufferedImage(500, 500, BufferedImage.TRANSLUCENT);
    Graphics2D newGraphics = (Graphics2D) newBufferedImage.getGraphics();

    Integer oldWidth = oldBufferedImage.getWidth();
    Integer oldHeight = oldBufferedImage.getHeight();

    if(Integer.compare(oldWidth, oldHeight) == 0){
      newGraphics.drawImage(oldBufferedImage, 0,0, 500, 500, null);
    } else if(Integer.compare(oldWidth, oldHeight) > 0) {
      Integer newHeight = (int)(oldHeight * 500.0) / oldWidth;
      newGraphics.drawImage(oldBufferedImage, 0,250 - (newHeight / 2), 500, newHeight, null);
    } else {
      Integer newWidth = (int)(oldWidth * 500.0) / oldHeight;
      newGraphics.drawImage(oldBufferedImage, 250 - (newWidth / 2),0, newWidth, 500, null);
    }

    Integer size = 70;

    newGraphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, size));

    FontMetrics metric = newGraphics.getFontMetrics();

    Integer xPos = (500 - metric.stringWidth(sticker.descricao())) / 2;

    newGraphics.setColor(Color.ORANGE);

    newGraphics.drawString(sticker.descricao(), xPos, 500 - 25);

    return newBufferedImage;
  }

}
