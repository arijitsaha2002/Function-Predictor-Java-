package Function_predictor;
import java.util.*;


/**
 * This class describes polynomials.
 */
public class Polynomials {
	private Double [] coef = null; /// store coefficients 
	private Integer degree = 0;    /// store degree

	/**
	 * Constructs a new instance.
	 *
	 *	Initially all coefficients are set to 0
	 * @param      degree  
	 */
	public Polynomials(Integer degree) {
		this.degree = degree;
		this.coef = new Double [degree + 1];
		for (int i = 0; i <= degree; i++) {
			coef[i] = 0.0;
		}
	}


	/**
	 * Remove all zero leading coefficients
	 */
	public void makeStandard() {
		for (int i = degree ; i >= 0 ; i-- ) {
			if (Math.abs( coef[i] ) < .00001 ) degree --;
			else break;
		}
	}

	/**
	 * Adds two Polynomials and return result
	 *
	 * @param      P     Polynomial to be added
	 *
	 * @return     resulting polynomial
	 */
	public Polynomials add(Polynomials P) {
		Integer degree_ = Math.max(degree , P.degree);
		Polynomials result = new Polynomials(degree_);
		for (int i = 0; i <= degree_ ; i++) {
			result.coef[i] = getAn(i) + P.getAn(i);
		}
		return result;
	}

	/**
	 * Return coefficent of X^n if n in range
	 * otherwise 0
	 *
	 * @param      n     
	 *
	 * @return     coefficent of X^n
	 */
	public Double getAn(Integer n) {
		if (degree < n) return 0.0;
		return coef[n];
	}


	/**
	 * Multiply current polynomial with a scaler
	 *
	 * @param      s     number to be multiplied
	 *
	 * @return     resulting polynomial
	 */
	public Polynomials scalerProduct(Double s) {
		Polynomials result = new Polynomials(degree);
		for (int i = 0; i <= degree ; i++) {
			result.coef[i] = coef[i] * s;
		}
		return result;
	}


	/**
	 * Sets a new value to a coefficient
	 *
	 * @param      n      coefficient index
	 * @param      value  new value
	 */
	public void setCoef(Integer n, Double value) {
		this.coef[n] = value;
	}

	/**
	 * Multiply two polynomials 
	 *
	 * @param      P     Polynomials to be multiplied
	 *
	 * @return     resulting Polynomial
	 */
	public Polynomials Multply( Polynomials P) {
		Integer degree_ = degree + P.degree;
		Polynomials result = new Polynomials(degree_);
		for (int i = 0 ; i <= degree ; i++) {
			for (int j = 0; j <= P.degree; j++) {
				result.coef[i + j] += P.getAn(j) * getAn(i);
			}
		}
		return result;
	}

	/**
	 * Return value of this polynomial for a perticular x
	 *
	 * @param      x     
	 *
	 * @return     value of the polynomial
	 */
	public Double FunctionValue(Double x) {
		Double result = 0.0;
		for (int i = degree ; i >= 0 ; i++) {
			result = result * x + getAn(i);
		}
		return result;
	}

	/**
	 * Prints a polynomial.
	 */
	public void PrintPolynomial(){
		makeStandard();
		for (int i = 0 ; i <= degree ; i++) {
			System.out.print(Math.round(coef[i]*1000)/1000+"X^"+i+"  ");
		}
		System.out.println();
	}
}