package org.mobiletrain.shudugame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;


public class ShuduView extends View{
	
	//单元格的宽度和高度
	private float width;
	private float height;
	
	private Game game=new Game();
	private int seletedX;
	private int seletedY;
	private int selectedX;
	private int selectedY;
	public ShuduView(Context context) {
		super(context);	
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	    this.width=w/9f;
	    this.height=h/9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint backgroundPaint=new Paint();
		backgroundPaint.setColor(getResources().getColor(R.color.shudu_background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        
        Paint darkPaint=new Paint();
        darkPaint.setColor(getResources().getColor(R.color.shudu_dark));

        Paint hilitePaint=new Paint();
        darkPaint.setColor(getResources().getColor(R.color.shudu_hilite));

        Paint lightPaint=new Paint();
        darkPaint.setColor(getResources().getColor(R.color.shudu_light));
        for(int i=0;i<9;i++){
        	
            canvas.drawLine(0,i*height,getWidth(),i*height,lightPaint);
            canvas.drawLine(0,i*height+1,getWidth(),i*height+1,hilitePaint);
            canvas.drawLine(i*width,0,i*width,getHeight(),lightPaint);
            canvas.drawLine(i*width+1,0,i*width+1,getHeight(),hilitePaint);  
        }
        
        for(int i=0;i<9;i++){
             if(i%3!=0){
            	 continue;
            	 
             }

             canvas.drawLine(0,i*height,getWidth(),i*height,lightPaint);
             canvas.drawLine(0,i*height+1,getWidth(),i*height+1,hilitePaint);
             canvas.drawLine(i*width,0,i*width,getHeight(),lightPaint);
             canvas.drawLine(i*width+1,0,i*width+1,getHeight(),hilitePaint);  
        }
        
        Paint numberPaint=new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        
        numberPaint.setTextSize(height*0.75f);
        numberPaint.setTextAlign(Paint.Align.CENTER);
       
       FontMetrics fm = numberPaint.getFontMetrics();
        
        
       float x=width/2;
       
	float y=height/2-(fm.ascent+fm.descent)/2;
          for(int i=0;i<9;i++){
          for(int j=0;j<9;j++){
	         canvas.drawText(game.getTileString(i,j),i*width+x,j*height+y,numberPaint);
          } 
     }
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction()!=MotionEvent.ACTION_DOWN){
			return super.onTouchEvent(event);
		}
			
	
	  seletedX=(int)(event.getX()/width);
	  seletedY=(int)(event.getY()/height);
	
	int used[] =game.getUsedTilesByCoor(seletedX,seletedY);
	StringBuffer sb=new StringBuffer();
	
	for(int i=0;i<used.length;i++){
		sb.append(used[i]);
	}
	 
	KeyDialog keyDialog=new KeyDialog(getContext(),used);
    keyDialog.show();
	
	//LayoutInflater layoutInflater=LayoutInflater.from(this.getContext());
	//View layoutView=layoutInflater.inflate(R.layout.dialog,null);
	//  TextView textView=(TextView)layoutView.findViewById(R.id.usedTextId);
	//textView.setText(sb.toString());
	//AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
	//builder.setView(layoutView);
	//AlertDialog dialog=builder.create();
	//dialog.show();
	return true;
	}
	public  void setSelectedTile(int tile){
		if(game.setTileIfValid(selectedX,selectedY,tile)){
			invalidate();
		}
	}


}
	
	
	



    


        