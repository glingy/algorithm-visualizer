package ling.gregory.gui;

import ling.gregory.Util;
import ling.gregory.structures.Structure;

import javax.swing.*;
import java.awt.*;

public class LocatedStructure {
  public Structure structure;
  public Rectangle pos;

  public LocatedStructure(LocatedStructure other) {
    structure = Util.copy(other.structure);
    pos = (Rectangle) other.pos.clone();
  }

  public LocatedStructure(Structure structure, Rectangle pos) {
    this.structure = structure;
    this.pos = pos;
  }
}
