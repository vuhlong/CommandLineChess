import java.util.ArrayList;

public class AI {
    private State S;
    //private int count;

    public AI(State S) {
        this.S = S;
    }

    public int[] CalculateNextMove() {
        ArrayList<State> Childs = S.ChildsOf(1);
        State temp = null;
        int result = -Integer.MAX_VALUE;
        //this.count = 0;
        for (State w : Childs) {
        	//count += 1;
            int min = AlphaBeta(w, 4, -Integer.MAX_VALUE, Integer.MAX_VALUE, false);
            //System.out.print(min+ " ");
            if (result <= min) {
                result = min;
                temp = w;
            }
        }
        //System.out.println("Number Node: " + count);
        return this.S.GenerateMove(temp);
    }

    public int AlphaBeta(State S, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if(depth==0 || S.whitepoint >= 6 || S.blackpoint >= 6) {
            if(maximizingPlayer) return S.evaluation(0);
            else return S.evaluation(1);
        }
        if(maximizingPlayer) {
            int V = -Integer.MAX_VALUE;
            ArrayList<State> Childs = S.ChildsOf(1);
            for (State u : Childs) {
            	//count += 1;
                V = Math.max(V,AlphaBeta(u,depth-1,alpha,beta,false));
                alpha = Math.max(alpha, V);
                if(beta <= alpha) break;
            }
            return V;
        }
        else {
            int V = Integer.MAX_VALUE;
            ArrayList<State> Childs = S.ChildsOf(0);
            for (State u : Childs) {
            	//count += 1;
                V = Math.min(V,AlphaBeta(u,depth-1,alpha,beta,true));
                beta = Math.min(beta, V);
                if(beta <= alpha) break;
            }
            return V;
        }
    }
}
