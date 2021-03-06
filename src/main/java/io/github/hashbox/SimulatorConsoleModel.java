package io.github.hashbox;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by js on 2017. 5. 21..
 */
public class SimulatorConsoleModel extends AbstractTableModel {
	private List<Car> consoleList;

	public void setConsoleList(List<Car> consoleList) {
		this.consoleList = consoleList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Car car = consoleList.get(rowIndex);
		Object val = null;
		if(consoleList == null) {
			val = "??";
		}
		else {
			switch (columnIndex) {
				case 0:
					val = car.getName();
					break;
				case 1:
					val = car.getLocatedToString() + "(" + car.getLocate() + "/" + (int)car.getDistance() + ")";
					break;
				case 2:
					if (car.getDestination() != null) {
						val = car.getDestination().getName();
					} else {
						val = "목적지 도착(" + secondToString((((ClientCar)car).getnTime()*5)) + ")";
					}
					break;
				case 3:
					if (car.getRoute() != null) {
						val = car.getRoute().toString();
					}
					else {
						val = "경로 없음";
					}
			}
		}
		return val;
	}

	public String secondToString(int second) {
		int minute = second / 60;
		second = second % 60;
		return minute + "분 " + second + "초";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class type = String.class;

		return type;
	}

	@Override
	public String getColumnName(int column) {
		String name = "??";
		switch (column) {
			case 0:
				name = "차량이름";
				break;
			case 1:
				name = "현재위치";
				break;
			case 2:
				name = "목적지";
				break;
			case 3:
				name = "남은 경로";
				break;
		}

		return name;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		int result = 0;
		if(consoleList != null) {
			result = consoleList.size();
		}
		return result;
	}

	public List<Car> getConsoleList() {
		return consoleList;
	}

	public SimulatorConsoleModel(List<Car> consoleList) {
		this.consoleList = consoleList;
	}
}
