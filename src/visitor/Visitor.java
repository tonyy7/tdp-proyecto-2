package visitor;

import criatura.Cuerpo;
import criatura.Snake;
import ente.alimento.Alimento1;
import ente.alimento.Alimento2;
import ente.alimento.Alimento3;
import ente.alimento.Alimento4;
import ente.alimento.Alimento5;
import ente.pared.Pared;
import ente.powerUp.PowerUp1;
import ente.powerUp.PowerUp2;
import ente.powerUp.PowerUp3;

public interface Visitor {
	
	public void visitAlimento1(Alimento1 a);
	public void visitAlimento2(Alimento2 a);
	public void visitAlimento3(Alimento3 a);
	public void visitAlimento4(Alimento4 a);
	public void visitAlimento5(Alimento5 a);
	public void visitPowerUp1(PowerUp1 p);
	public void visitPowerUp2(PowerUp2 p);
	public void visitPowerUp3(PowerUp3 p);
	public void visitCuerpo(Cuerpo c);
	public void visitPared(Pared p);
}
