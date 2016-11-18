package org.mobiletrain.shudugame;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class KeyDialog extends Dialog{
	private final View keys[]= new View[9];
	private final int used[];
	private Object shuduView;
	

	public KeyDialog(Context context,int [] used) {
		super(context);
		this.used=used;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("KeyDialog");
		setContentView(R.layout.keypad);
		findViews();
		for(int i=0;i <used.length;i++){
			if(used[i]!=0){
				keys[used[i]].setVisibility(View.INVISIBLE);
				
			}
			
		}
		setListeners();
	}
    
	
	private void findViews(){
		keys[0]=findViewById(R.id.keypad_1);
		keys[0]=findViewById(R.id.keypad_2);
		keys[0]=findViewById(R.id.keypad_3);
		keys[0]=findViewById(R.id.keypad_4);
		keys[0]=findViewById(R.id.keypad_5);
		keys[0]=findViewById(R.id.keypad_6);
		keys[0]=findViewById(R.id.keypad_7);
		keys[0]=findViewById(R.id.keypad_8);
		keys[0]=findViewById(R.id.keypad_9);
	}
	private void returnResult(int tile){
		
		dismiss();
	
	}


	private void setListeners(){
    	for (int i=0;i<keys.length;i++){
    		final int t=i+1;
    		keys[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v){
			          returnResult(t);
				}
			});
      }
   }
}