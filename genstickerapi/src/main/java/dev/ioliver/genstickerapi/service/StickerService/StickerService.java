package dev.ioliver.genstickerapi.service.StickerService;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
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

    FontMetrics metrics = newGraphics.getFontMetrics();

    GlyphVector glyphVector = newGraphics.getFont().createGlyphVector(metrics.getFontRenderContext(), sticker.descricao());

    Shape shape = glyphVector.getOutline();

    Stroke stroke = newGraphics.getStroke();

    newGraphics.setColor(Color.BLACK);

    newGraphics.setStroke(new BasicStroke(10f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));

    Integer xPos = (500 - metrics.stringWidth(sticker.descricao())) / 2;

    newGraphics.translate(xPos, 500 - 25);

    newGraphics.draw(shape);

    newGraphics.setColor(Color.ORANGE);

    newGraphics.fill(shape);

    return newBufferedImage;
  }

}
