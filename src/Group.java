public interface Group<T> {
    /**
    * Performs the binary operation, as defined by the group, of one object with the other specified
    * object. The implementer must take care to ensure that the binary operation is
    * <ul>
    * <li><b>closed</b> for the parameter type <code>T</code>. That is, the result of the binary
    * operation is a valid member of the set that defines the type <code>T</code> (taking the
    * denotational semantics view of data types). For example, addition is a binary operation
    * that is closed for integers, but division is not.</li>
    * <li><b>associative</b>. That is, for any elements <code>x</code>, <code>y</code>, and
    * <code>z</code> in this group, <code>binaryOperation(binaryOperation(x, y), z)</code> is
    * equal to <code>binaryOperation(x, binaryOperation(y, z))</code>. For example, addition is
    * an associative binary operation for integers.</li>
    * <li>respectful of the identity element. That is, for any element <code>x</code> in this
    * group and the identity element <code>e</code> of this group,
    * <code>binaryOperation(x, e)</code> is equal to <code>x</code>, and
    * <code>binaryOperation(e, x)</code> is also equal to <code>x</code>. For example,
    * <code>0</code> is the identity element of the group of integers under addition.</li>
    * </ul>
    *
    * @param one the object that is the first argument of the binary operation.
    * @param other the other object (the second argument of the binary operation) to be combined with
    * this object as per the group’s binary operation.
    * @return the result of the binary operation on this object with the other specified object.
    */
    T binaryOperation(T one, T other);
    /**
    * @return the identity element of this group.
    */
    T identity();
    /**
    * In a group, every element <code>x</code> must have its inverse, which is an element <code>y</code>
    * in the group such that <code>binaryOperation(x, y)</code> is equal to
    * <code>binaryOperation(y, x)</code>, and both yield the identity element of the group. For example,
    * for the group of integers under addition, the negative of any integer is its inverse.
    *
    * @return the inverse of this object.
    */
    T inverseOf(T t);
    /**
    * This is a utility function, serving as the definition of exponentiation for this group.
    * Exponentiation is defined as <code>exponent(t, 0)</code> being the <code>identity()</code>
    * element, and <code>exponent(t, n)</code> being <code>binaryOperation(t, exponent(t, n-1))</code>.
    *
    * @param t the group element serving as the base.
    * @param k the integer exponent, indicating the number of times the binary operation is applied
    * on <code>t</code>. The exponent must be a non-negative integer value.
    * @return the result of the binary operation applied <code>k</code> times on <code>t</code>.
    */
    default T exponent(T t, int k) {
    if (k < 0)
      throw new IllegalArgumentException("The exponent must be a non-negative integer value.");
      return k == 0 ? identity() : binaryOperation(t, exponent(t, k - 1));
    }
}