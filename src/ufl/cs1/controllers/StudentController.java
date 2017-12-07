package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.controllers.benchmark.Devastator;
import game.models.*;
import game.view.GameView;
import game.models.Actor;
import game.models.Maze;

import java.util.List;

public final class StudentController implements DefenderController {

	public void init(Game game) {


/*
		System.out.print(game.getCurMaze().getPowerPillNodes().get(0).getX()+ ", ");
		System.out.println(game.getCurMaze().getPowerPillNodes().get(0).getY());
		System.out.print(game.getCurMaze().getPowerPillNodes().get(1).getX()+ ", ");
		System.out.println(game.getCurMaze().getPowerPillNodes().get(1).getY());
		System.out.print(game.getCurMaze().getPowerPillNodes().get(2).getX()+ ", ");
		System.out.println(game.getCurMaze().getPowerPillNodes().get(2).getY());
		System.out.print(game.getCurMaze().getPowerPillNodes().get(3).getX()+ ", ");
		System.out.println(game.getCurMaze().getPowerPillNodes().get(3).getY());
*/
	}

	public void shutdown(Game game) {
	}

	public int[] update(Game game, long timeDue) {
		int[] actions = new int[Game.NUM_DEFENDER];

		Actor MsPac = game.getAttacker();
		Defender blinky = game.getDefender(0);		//red ghost
		Defender pinky = game.getDefender(1);		//pink ghost
		Defender inky = game.getDefender(2);		//orange ghost
		Defender sue = game.getDefender (3);		//blue ghost
		Attacker attacker = game.getAttacker();

		actions[0] = blinkyAlgorithim(game, MsPac, blinky);
		actions[1] = pinkyAlgorithim(game, MsPac, pinky);
		actions[2] = inkyAlgorithim(game, MsPac, inky);
		actions[3] = sueAlgorithim(game, MsPac, sue);

		//scatter(actions, blinky, pinky, inky, sue);

		//System.out.println(blinky.getLocation().getPathDistance(MsPac.getLocation()));
		//System.out.println(MsPac.getLocation().getNeighbor(MsPac.getDirection() ));
		//System.out.println(attacker.getPossibleLocations(false));
		return actions;

	}
	public int blinkyAlgorithim(Game game, Actor MsPac,Defender blinky){
		if (blinky.getPossibleDirs().size() != 0) {
			if(game.getPowerPillList().size() != 0) {
				if (!(blinky.isVulnerable())) {											//blinky is not vulnerable
					if (blinky.getLocation().getPathDistance(MsPac.getLocation()) <=40){
						return blinky.getNextDir(MsPac.getLocation(),true);
					} else
						return blinky.getNextDir(blinky.getTargetNode(game.getPowerPillList(), true), true);
				} else
					return blinky.getNextDir(MsPac.getLocation(),false);

			} else
				return blinky.getNextDir(MsPac.getLocation(),true);

		} else
			return -1;
	}

	public int pinkyAlgorithim(Game game, Actor MsPac, Defender pinky){
		if (pinky.getPossibleDirs().size() != 0) {
			if (!(pinky.isVulnerable())) {
				return pinky.getNextDir(MsPac.getLocation(), true);
			} else
				return pinky.getNextDir(MsPac.getLocation(), false);

		}else
			return -1;
	}

	//orange ghost
	public int inkyAlgorithim(Game game, Actor MsPac, Defender inky) {
		Node nullFlag = null;
		Attacker attacker = game.getAttacker();

		if(inky.getPossibleDirs().size() !=0) {
			//if (!(inky.isVulnerable())) {
			//	return inky.getNextDir(inky.getTargetNode(attacker.getPossibleLocations(false),true),true);
			if (!(inky.isVulnerable())) {
				if(MsPac.getLocation().getNeighbor(MsPac.getDirection()) != nullFlag){
					return inky.getNextDir( MsPac.getLocation().getNeighbor(MsPac.getDirection() ),true );
				} else
					return inky.getNextDir(MsPac.getLocation(), true);

			}else
				return inky.getNextDir(MsPac.getLocation(), false);

		}else
			return -1;

	}

	//
	public int sueAlgorithim(Game game, Actor MsPac, Defender sue){

		if(sue.getPossibleDirs().size() != 0){
			if(!(sue.isVulnerable())){
				if(game.getPillList().size() != 0){
					if (sue.getLocation().getPathDistance(MsPac.getLocation()) <=60)
						return sue.getNextDir(MsPac.getLocation(),true);
					else
						return sue.getNextDir(sue.getTargetNode(game.getPillList(), true), true);
				}else
					return -1;

			}else
				return sue.getNextDir(MsPac.getLocation(), false);

		}else
			return -1;

	}


}



//System.out.println( MsPac.getLocation() );
//System.out.print(ghost1.getLocation().getX()+", ");
//System.out.println(ghost1.getLocation().getY());
//Node capture = game.getCurMaze().getPowerPillNodes().get(3);



//Actor blinky = ghost1;
//actions[0] = ghost1.getPossibleDirs().get(Game.rng.nextInt(ghost1.getPossibleDirs().size()));
//actions[0] = ghost1.getNextDir(capture,true);

