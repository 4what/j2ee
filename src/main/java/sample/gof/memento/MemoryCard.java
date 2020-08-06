package sample.gof.memento;

import java.util.ArrayList;
import java.util.List;

public class MemoryCard {
	private List<Record> records = new ArrayList<>();

	public void add(Record record) {
		records.add(record);
	}

	public Record get(int index) {
		return records.get(index);
	}
}
