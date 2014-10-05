import java.util.LinkedList;
<<<<<<< HEAD

=======
>>>>>>> 1fd8d6c6406c5ae5da82d7cc59000f8f09b67499
public class RPNAddSub {
  // @include
  public static int eval(String RPNExpression) {
    LinkedList<Integer> intermediateResults = new LinkedList<>();
    String delimiter = ",";
    String[] symbols = RPNExpression.split(delimiter);
<<<<<<< HEAD
    for (String token : symbols) {
      if (token.length() == 1 && "+-".contains(token)) {
        int y = intermediateResults.pop();
        int x = intermediateResults.pop();
        switch (token.charAt(0)) {
          case '+':
            intermediateResults.push(x + y);
            break;
          case '-':
            intermediateResults.push(x - y);
            break;
        }
      } else { // token is a number.
        intermediateResults.push(Integer.parseInt(token));
      }
    }
    return intermediateResults.pop();
  }
  // @exclude

=======
        for (String token : symbols) {
            if (token.length() == 1 && "+-".contains(token)) {
                int y = intermediateResults.pop();
                int x = intermediateResults.pop();
                switch (token.charAt(0)) {
                    case '+':
                        intermediateResults.push(x + y);
                        break;
                    case '-':
                        intermediateResults.push(x - y);
                        break;
                }
            } else { // token is a number.
                intermediateResults.push(Integer.parseInt(token));
            }
        }
    return intermediateResults.pop();
  }
    // @exclude
    //
    // break;
>>>>>>> 1fd8d6c6406c5ae5da82d7cc59000f8f09b67499
  public static void main(String[] args) {
    assert (-8 == eval("2,-10,+"));
    assert (-5 == eval("5,10,-"));
    assert (6 == eval("-10,-16,-"));
    assert (12 == eval("10,2,+"));
    assert (2 == eval("1,2,+,3,4,-,+"));
  }
}

<<<<<<< HEAD


=======
>>>>>>> 1fd8d6c6406c5ae5da82d7cc59000f8f09b67499
