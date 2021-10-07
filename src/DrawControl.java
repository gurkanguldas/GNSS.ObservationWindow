
public class DrawControl {
	static int[] Modified_x;
	static int[] Modified_y;
	static int Direction;
	static int j = 0;
	private static boolean Everyone = true;
public static void Up( Boolean Control_y) {
	Modified_x = new int[362];
	Modified_y = new int[362];
	Everyone = true;
	j=0;
if(Control_y)
		
	for (int i = 0 , j = 0; i < 360; i++ , j++) {

		if(Drawing.x[(359+i)%360]<60) {
		    if(Drawing.x[i]>Drawing.x[(359+i)%360] && Everyone) {Everyone=false;
				Modified_x[(361+j)%362]=25;
				Modified_x[j]=25;   Modified_y[j]=25; j++;
				Modified_x[j]=1645; Modified_y[j]=25; j++;
				
				Modified_x[j]=1645;
				Modified_y[j]=Drawing.y[i]; 
			}
			else {
				Modified_x[j]=Drawing.x[i];
				Modified_y[j]=Drawing.y[i];
			}
		}
		else {
			Modified_x[j]=Drawing.x[i];
			Modified_y[j]=Drawing.y[i];
		}
	}
	
	else
		
		for (int i = 0 , j = 0; i < 360; i++ , j++) {
			if(Drawing.x[(359+i)%360]>1600){
				if(Drawing.x[i]<Drawing.x[(359+i)%360] && Everyone) {Everyone=false;
					Modified_x[(361+j)%362]=1645;
					Modified_x[j]=1645;   Modified_y[j]=746; j++;
					Modified_x[j]=25; Modified_y[j]=746; j++;
					
					Modified_x[j]=25;
					Modified_y[j]=Drawing.y[i]; 
				}
				else {
					Modified_x[j]=Drawing.x[i];
					Modified_y[j]=Drawing.y[i];
				}
			}
			else {
				Modified_x[j]=Drawing.x[i];
				Modified_y[j]=Drawing.y[i];
			}
		}
}
public static void Left() {
	j=0;
	Modified_x = new int[360];
	Modified_y = new int[360];

	if(Drawing.x[0]<=823) {
		for (int i = 0,a=0; i < Drawing.x.length; i++) {
			if(Drawing.x[(359+i)%360]<200 && Drawing.x[i]>1500) {
				Drawing.x[(359+i)%360]=25;
				for (int k = i; k < Drawing.x.length; i++,j++,k++) {
					Modified_x[j]=Drawing.x[i];
					Modified_y[j]=Drawing.y[i];
					if(Drawing.x[i]<200 && Drawing.x[(359+i)%360]>1500) {
						Modified_x[j]=1645;
						Drawing.x[a]=25;
						Drawing.y[a]=Drawing.y[i];a++;
						break;
						}
				}
			}
			else {
				Drawing.x[a]=Drawing.x[i];
				Drawing.y[a]=Drawing.y[i];a++;
			}
		}
		Modified_x[0]=1645;
	}
	else {
		for (int i = 0,a=0; i < Drawing.x.length; i++) {
			if(Drawing.x[i]<200 && Drawing.x[(359+i)%360]>1500) {
				Drawing.x[(359+i)%360]=1645;
				for (int k = i; k < Drawing.x.length; i++,j++,k++) {
					Modified_x[j]=Drawing.x[i];
					Modified_y[j]=Drawing.y[i];
					if(Drawing.x[(359+i)%360]<200 && Drawing.x[i]>1500) {
						Modified_x[j]=25;
						Drawing.x[a]=1645;
						Drawing.y[a]=Drawing.y[i];a++;
						break;
						}
				}
			}
			else {
				Drawing.x[a]=Drawing.x[i];
				Drawing.y[a]=Drawing.y[i];a++;
			}
		}
		Modified_x[0]=25;
	}
	
}
public static void Kill() {
	j=0;
	Direction=0;
}
}
