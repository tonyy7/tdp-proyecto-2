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
	
	public int visitAlimento1(Alimento1 a);
	public int visitAlimento2(Alimento2 a);
	public int visitAlimento3(Alimento3 a);
	public int visitAlimento4(Alimento4 a);
	public int visitAlimento5(Alimento5 a);
	public int visitCuerpo(Cuerpo c);
	public int visitPowerUp1(PowerUp1 p);
	public int visitPowerUp2(PowerUp2 p);
	public int visitPowerUp3(PowerUp3 p);
	public void visitPared(Pared p);
}
