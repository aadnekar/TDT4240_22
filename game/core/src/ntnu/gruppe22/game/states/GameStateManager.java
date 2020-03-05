package ntnu.gruppe22.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

        private Stack<State> states; //stack of states

        public GameStateManager(){
            states = new Stack<State>();
        }

        //push the state on the stack
        public void push(State state){
            states.push(state);
        }

        //pops the state of the stack
        public void pop(){
            states.pop();
        }

        public void set(State state){
            states.pop();
            states.push(state);
        }


        public void update(float dt){
            states.peek().update(dt);
        }

        public void render(SpriteBatch sb){
            states.peek().render(sb);
        }


}
