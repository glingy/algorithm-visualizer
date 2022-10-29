package visualizer.gui;

import visualizer.structures.Structure;

import javax.swing.*;
import java.awt.*;

public class StructureRenderer extends JPanel {
  Structure structure;

  public StructureRenderer(Structure structure) {
    this.structure = structure;
    setBorder(BorderFactory.createTitledBorder(structure.getTitle()));
  }

  @Override
  public void paintComponent(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    Insets insets = getInsets();
    Rectangle bounds = g.getClipBounds();
    bounds.x += insets.left;
    bounds.y += insets.top;
    bounds.width -= insets.left + insets.right;
    bounds.height -= insets.top + insets.bottom;
    structure.draw(g, bounds);
  }
}
