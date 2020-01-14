package common;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
		private String str;

		public MyComboBoxRenderer(String str) {
			this.str = str;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean hasFocus) {
			if (index == -1 && value == null)
				setText(str);
			else
				setText(value.toString());
			return this;
		}
	}