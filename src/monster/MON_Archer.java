package monster;

import java.util.Random;

import entity.Entity;
import main.GameLoop;
import object.OBJ_Arrow;

public class MON_Archer extends Entity {

    GameLoop gp;

    public MON_Archer(GameLoop gp){
        super(gp);

        this.gp= gp;
        
        type = 3;
        name = "Archer";
        speed = 1;
        maxLife = 2;
        life = maxLife;
        projectile = new OBJ_Arrow(gp);
        exp = 1;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage(); 
        update();
    }

    public void setAction() {

        int i = new Random().nextInt(75)+1;
        if (i > 74 && projectile.alive == false && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void update() {

        collisionOn = false; 
		gp.checker.checkTile(this);
		gp.checker.checkObject(this, false);
		gp.checker.checkEntity(this, gp.npc);
		gp.checker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.checker.checkPlayer(this);

		if (this.type == 1 && this.type == 2 && contactPlayer == true || this.type == 1 && this.type == 3 && contactPlayer == true) {
			damagePlayer(attack);
		}

		if(invicible == true) {
			invicibleCounter++;
			if(invicibleCounter > 10) {
				invicible = false;
				invicibleCounter = 0;
			}
		}

		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
        setAction();
	}

    public void getImage(){

        up1 = setup("monster/ArcherUp",gp.titleSize,gp.titleSize);
        down1 = setup("monster/ArcherBas",gp.titleSize,gp.titleSize);
        left1 = setup("monster/ArcherLeft",gp.titleSize,gp.titleSize);
        right1 = setup("monster/ArcherRight",gp.titleSize,gp.titleSize);
    }
    
}
