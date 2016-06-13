package src.game;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;

public class Upgrades extends Frame {

	private static final long serialVersionUID = 10L;

	public Upgrades() {
		setTitle("Upgrades");
		setSize(350, 300);
		setVisible(true);
		setLayout(null);
		
		Label hp_l = new Label("HP");
		Label hp_lv = new Label("100%");
		Button hp_b = new Button("+");
		hp_l.setBounds(10, 40, 100, 30);
		hp_lv.setBounds(120, 40, 60, 30);
		hp_b.setBounds(190, 40, 30, 30);
		add(hp_l);
		add(hp_lv);
		add(hp_b);
		
		Label speed_l = new Label("Speed");
		Label speed_lv = new Label("100%");
		Button speed_b = new Button("+");
		speed_l.setBounds(10, 80, 100, 30);
		speed_lv.setBounds(120, 80, 60, 30);
		speed_b.setBounds(190, 80, 30, 30);
		add(speed_l);
		add(speed_lv);
		add(speed_b);
		
		Label accuracy_l = new Label("Accuracy");
		Label accuracy_lv = new Label("100%");
		Button accuracy_b = new Button("+");
		accuracy_l.setBounds(10, 120, 100, 30);
		accuracy_lv.setBounds(120, 120, 60, 30);
		accuracy_b.setBounds(190, 120, 30, 30);
		add(accuracy_l);
		add(accuracy_lv);
		add(accuracy_b);
		
		Label damage_l = new Label("Damage");
		Label damage_lv = new Label("100%");
		Button damage_b = new Button("+");
		damage_l.setBounds(10, 160, 100, 30);
		damage_lv.setBounds(120, 160, 60, 30);
		damage_b.setBounds(190, 160, 30, 30);
		add(damage_l);
		add(damage_lv);
		add(damage_b);
		
		Label firing_speed_l = new Label("Firing Speed");
		Label firing_speed_lv = new Label("100%");
		Button firing_speed_b = new Button("+");
		firing_speed_l.setBounds(10, 200, 100, 30);
		firing_speed_lv.setBounds(120, 200, 60, 30);
		firing_speed_b.setBounds(190, 200, 30, 30);
		add(firing_speed_l);
		add(firing_speed_lv);
		add(firing_speed_b);
		
		Label max_health_l = new Label("Max Health");
		Label max_health_lv = new Label("100%");
		Button max_health_b = new Button("+");
		max_health_l.setBounds(10, 240, 100, 30);
		max_health_lv.setBounds(120, 240, 60, 30);
		max_health_b.setBounds(190, 240, 30, 30);
		add(max_health_l);
		add(max_health_lv);
		add(max_health_b);
		
		Button close = new Button("Close");
		close.setBounds(250, 240, 60, 30);
		add(close);
	}
	
	public static void main(String args[]) {
		new Upgrades();
	}
}