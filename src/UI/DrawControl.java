package UI;

public class DrawControl {
	public int[] Modified_x;
	public int[] Modified_y;
	public int Direction;
	public int j = 0;
	
	private boolean Everyone = true;
	private Drawing drawing;

	public DrawControl(Drawing drawing) 
	{
		this.drawing = drawing;
	}

	public void Up(Boolean Control_y) 
	{
		Modified_x = new int[362];
		Modified_y = new int[362];
		Everyone = true;
		j = 0;
		if (Control_y) 
		{
			for (int i = 0, j = 0; i < 360; i++, j++) 
			{
				if (drawing.x[(359 + i) % 360] < 60) 
				{
					if (drawing.x[i] > drawing.x[(359 + i) % 360] && Everyone) 
					{
						Everyone = false;
						Modified_x[(361 + j) % 362] = 25;
						Modified_x[j] = 25;
						Modified_y[j] = 25;
						j++;
						Modified_x[j] = 1645;
						Modified_y[j] = 25;
						j++;

						Modified_x[j] = 1645;
						Modified_y[j] = drawing.y[i];
					} 
					else 
					{
						Modified_x[j] = drawing.x[i];
						Modified_y[j] = drawing.y[i];
					}
				} 
				else 
				{
					Modified_x[j] = drawing.x[i];
					Modified_y[j] = drawing.y[i];
				}
			}
		}
		else 
		{

			for (int i = 0, j = 0; i < 360; i++, j++) 
			{
				if (drawing.x[(359 + i) % 360] > 1600) 
				{
					if (drawing.x[i] < drawing.x[(359 + i) % 360] && Everyone) 
					{
						Everyone = false;
						Modified_x[(361 + j) % 362] = 1645;
						Modified_x[j] = 1645;
						Modified_y[j] = 746;
						j++;
						Modified_x[j] = 25;
						Modified_y[j] = 746;
						j++;

						Modified_x[j] = 25;
						Modified_y[j] = drawing.y[i];
					} 
					else 
					{
						Modified_x[j] = drawing.x[i];
						Modified_y[j] = drawing.y[i];
					}
				} 
				else 
				{
					Modified_x[j] = drawing.x[i];
					Modified_y[j] = drawing.y[i];
				}
			}
		}
	}

	public void Left() 
	{
		j = 0;
		Modified_x = new int[360];
		Modified_y = new int[360];

		if (drawing.x[0] <= 823) 
		{
			for (int i = 0, a = 0; i < drawing.x.length; i++) 
			{
				if (drawing.x[(359 + i) % 360] < 200 && drawing.x[i] > 1500) 
				{
					drawing.x[(359 + i) % 360] = 25;
					
					for (int k = i; k < drawing.x.length; i++, j++, k++) 
					{
						Modified_x[j] = drawing.x[i];
						Modified_y[j] = drawing.y[i];
						
						if (drawing.x[i] < 200 && drawing.x[(359 + i) % 360] > 1500) 
						{
							Modified_x[j] = 1645;
							drawing.x[a] = 25;
							drawing.y[a] = drawing.y[i];
							a++;
							break;
						}
					}
				} 
				else 
				{
					drawing.x[a] = drawing.x[i];
					drawing.y[a] = drawing.y[i];
					a++;
				}
			}
			
			Modified_x[0] = 1645;
		} 
		else 
		{
			for (int i = 0, a = 0; i < drawing.x.length; i++) 
			{
				if (drawing.x[i] < 200 && drawing.x[(359 + i) % 360] > 1500) 
				{
					drawing.x[(359 + i) % 360] = 1645;
					
					for (int k = i; k < drawing.x.length; i++, j++, k++) 
					{
						Modified_x[j] = drawing.x[i];
						Modified_y[j] = drawing.y[i];
						
						if (drawing.x[(359 + i) % 360] < 200 && drawing.x[i] > 1500) 
						{
							Modified_x[j] = 25;
							drawing.x[a] = 1645;
							drawing.y[a] = drawing.y[i];
							a++;
							break;
						}
					}
				}
				else 
				{
					drawing.x[a] = drawing.x[i];
					drawing.y[a] = drawing.y[i];
					a++;
				}
			}
			Modified_x[0] = 25;
		}
	}

	public void Kill() 
	{
		j = 0;
		Direction = 0;
	}
}
