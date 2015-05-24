package slr.automaton;

import java.util.Map;
import java.util.Set;

import slr.exception.InvalidTransitionException;

/**
 * Estado.
 * @author lucas
 */
public class State implements Comparable<State> {

	private String name;
	private boolean isFinal;
	private TransitionMap transitions;
	
	/**
	 * Construtor.
	 * @param name nome do estado.
	 * @param isFinal true caso o estado seja final.
	 * @param transitions transições do estado.
	 */
	public State(String name, boolean isFinal, TransitionMap transitions) {
		this.name = name;
		this.isFinal = isFinal;
		this.transitions = transitions;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new State(this.name, this.isFinal(), (TransitionMap) this.transitions.clone());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State state = (State) obj;
			return state.getName().equals(this.name);
		}
		
		return false;
	}

	@Override
	public int compareTo(State o) {
		return this.getName().compareTo(o.getName());
	}
	
	/**
	 * Definir se o estado é final.
	 * @param isFinal true se o estado é final.
	 */
	public void setIsFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	/**
	 * Obter o nome do estado.
	 * @return nome do estado.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Verificar se o estado é final.
	 * @return true caso o estado seja final.
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/**
	 * Transitar do estado para outro.
	 * @param symbol símbolo de entrada de transição.
	 * @return conjunto de estados de destino da transição.
	 * @throws InvalidTransitionException se não há uma transição pelo símbolo de entrada especificado.
	 */
	public Set<State> transit(char symbol) throws InvalidTransitionException {
		return this.transitions.get(symbol);
	}
	
	/**
	 * Obter os estados alcançáveis a partir deste.
	 * @return conjunto de estados.
	 */
	public Set<State> getReachableStates() {
		return this.transitions.getTargetStates();
	}

	/**
	 * Obter as transições do estado.
	 * @return mapa de transições.
	 */
	public Map<Character, Set<State>> getTransitions() {
		return this.transitions.getMap();
	}

}