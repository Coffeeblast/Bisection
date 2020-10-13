# Bisection
Little GUI app for finding a root of a function using bisection method. 

## Bisection method
Suppose we have function _f(x)_ continuous on interval _(a, b)_ and the values _f(a)_ 
and _f(b)_ have opposite signs, i.e. _f(a) * f(b) < 0_. Then there is at least one 
root _c_ within the interval, _i.e. f(c) = 0_. If there is exactly one root within 
the interval it can be found by the method of bisection.

Single step of bisection: 
To apply this method, test the sign of _f_ in the middle of interval _(a, b)_, at the 
point _m = (a + b)/2_, if _f(m)_ and _f(a)_ have the same signs, the root lies in interval 
_(m, b)_, othervise it lies in the interval _(a, m)_.

Apply in the cycle: 
repeat the single step of bisection, until the location of root is determined within
the desired accuracy _err_. 

## Project organization

The classes in project are used for:
* Logic part calculating bisection
  * class **Bisection**
    * the constructor takes _FunctionCalculator_ object that implements _f(x)_
    * implements bisection in _findRoot()_ method which returns _RootObject_
  * class **RootObject** holds result of bisection
    * contains _c_ in double _root_
    * contains number of iterations that was required in int _numberOfSteps_
  * interface **FunctionCalculator** 
    * implementations of this interface provide _f(x)_
    * e.g. class **CustomFunctionCalculator**
* GUI part
  * class **App** holds the application, contains also instance of _Bisection_ for the 
  bisection calculation and instance of _Graph_ for displaying _f_
  * class **Graph** extends _Plotter_ and overloads its _paintComponent()_ to draw graph
  of _f(x)_
  * class **Plotter** extends JComponent and contains methods for transforming between
  logical coordinates _(x, y)_ and pixel coordinates, as well as methods for drawing a line and 
  a grid
* main part - contains _main()_ method within **Main** class where FunctionCalculator is 
instantiated and passed to _App_ constructor
   
