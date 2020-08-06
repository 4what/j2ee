package sample.gof.memento;

public class Test {


	public static void main(String[] args) {
		MemoryCard memoryCard = new MemoryCard();

		Game game = new Game();

		game.setState("Chapter-1");

		memoryCard.add(game.save());

		game.setState("Chapter-100");

		game.load(memoryCard.get(0));
	}
}
