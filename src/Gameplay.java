

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener , ActionListener{
    
	private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];
    
    private boolean right=false;
    private boolean left=false;
    private boolean up=false;
    private boolean down=false;
    
    private boolean isPlaying=true;
    
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    
    private int lengthofsnake=3;
    
    private Timer timer;
    private int delay=100;
    
    private int [] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    private ImageIcon enemy;
    
    private Random random=new Random();
    
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);
    
    private int score=0;
    
    
    private int move=0;
    
    private ImageIcon snakeimage;
    
	private ImageIcon titleImage;
	
    public Gameplay(){
        
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	timer=new Timer(delay,this);
    	timer.start();
    }
    
    public void paint(Graphics g){
        
    	if(move==0){
    		snakexlength[2]=50;
    		snakexlength[1]=75;
    		snakexlength[0]=100;
    		
    		snakeylength[2]=100;
    		snakeylength[1]=100;
    		snakeylength[0]=100;
    	}
        //draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        
        //draw title image
        titleImage=new ImageIcon("heading11.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        
        //draw border for gameplay
        g.setColor(Color.BLACK);
        g.drawRect(24, 74, 851, 577);
        
        //draw backgroung for gameplay
        g.setColor(Color.YELLOW);
        g.fillRect(25, 75, 850, 575);
        
        //draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("cooper", Font.PLAIN,14));
        g.drawString("Points: "+score,50,50);
        
        //draw length
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("arial", Font.PLAIN,14));
//        g.drawString("Length:"+lengthofsnake,750,50);
//        
        
        
        rightmouth=new ImageIcon("green.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        
        for(int a=0;a<lengthofsnake;a++){
        	if(a==0 && right){
        		rightmouth=new ImageIcon("green.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                	
        	}
        	if(a==0 && left){
        		leftmouth=new ImageIcon("green.png");
        		leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                	
        	}
        	if(a==0 && up){
        		upmouth=new ImageIcon("green.png");
        		upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                	
        	}
        	if(a==0 && down){
        		downmouth=new ImageIcon("green.png");
        		downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                	
        	}
        	if(a!=0){
        		snakeimage=new ImageIcon("orenge.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
                
        	}
        }
        
        enemy =new ImageIcon("redflowr.png");
        
        if(snakexlength[0]==enemyxpos[xpos]&&snakeylength[0]==enemyypos[ypos]){
        	lengthofsnake++;
        	score++;
        	ypos=random.nextInt(23);
        	xpos=random.nextInt(34);
        	
        }
        enemy.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
        
        for(int b=1;b<lengthofsnake;b++){
			if(snakexlength[b]==snakexlength[0]&&snakeylength[b]==snakeylength[0]){
				left=false;
				right=false;
				up=false;
				down=false;
				isPlaying=false;
				//draw game over
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over",300,300);
				
				//draw reset
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Enter Space to RESTART",350,340);
			}
		}
        g.dispose();
        		
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(isPlaying){
			
		
		if(right){
			for(int r=lengthofsnake-1;r>=0;r--){
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--){
				if(r==0){
					snakexlength[r]=snakexlength[r]+25;
				}
				else{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]>850){
					snakexlength[r]=25;
				}
			}
			repaint();
		}
		if(left){
			for(int r=lengthofsnake-1;r>=0;r--){
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--){
				if(r==0){
					snakexlength[r]=snakexlength[r]-25;
				}
				else{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]<25){
					snakexlength[r]=850;
				}
			}
			repaint();
		}
		if(up){
			for(int r=lengthofsnake-1;r>=0;r--){
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--){
				if(r==0){
					snakeylength[r]=snakeylength[r]-25;
				}
				else{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]<75){
					snakeylength[r]=625;
				}
			}
			repaint();
		}
		if(down){
			for(int r=lengthofsnake-1;r>=0;r--){
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--){
				if(r==0){
					snakeylength[r]=snakeylength[r]+25;
				}
				else{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]>625){
					snakeylength[r]=75;
				}
			}
		}	
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			move=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			move++;
			isPlaying=true;
			if(!left){
				right=true;
			}else{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			move++;
			isPlaying=true;
			if(!right){
				left=true;
			}else{
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			move++;
			isPlaying=true;
			if(!down){
				up=true;
			}else{
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			move++;
			isPlaying=true;
			if(!up){
				down=true;
			}else{
				down=false;
				up=true;
			}
			left=false;
			right=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
