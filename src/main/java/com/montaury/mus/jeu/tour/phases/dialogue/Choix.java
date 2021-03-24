package com.montaury.mus.jeu.tour.phases.dialogue;

import java.lang.reflect.Type;
import java.util.List;

public abstract class Choix {
  private final TypeChoix type;
  private final int mise;

  protected Choix(TypeChoix type) {
    this(type, 0);
  }

  protected Choix(TypeChoix type, int mise) {
    this.type = type;
    this.mise = mise;
  }

  public TypeChoix type() {
    return type;
  }

  public boolean estUneMise(){
    if(this.type == TypeChoix.IMIDO || this.type == TypeChoix.GEHIAGO || this.type == TypeChoix.HORDAGO){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean est(TypeChoix type) {
    return this.type == type;
  }

  public int mise() {
    return mise;
  }

  public final List<TypeChoix> prochainsChoixPossibles() {
    return type.choixSuivantsPossibles();
  }

  public final boolean metFinAuDialogue() {
    return type.choixSuivantsPossibles().isEmpty();
  }

}
