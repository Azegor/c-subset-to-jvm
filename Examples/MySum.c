int bla(int y)
{
  int i;
  int x;
  x = 7;
  for (i = 0; i < 5; i = i + 1)
  {
	x = x + 1;
  }
  return x;
}

int bla()
{
  int y = 2;
  int test = 2 > 0 ? 7 < 1 ? 5 : 4 : 3;
  println(test);
  for(;;)
  {
	println(42);
	y = y - 1;
	if (y < 0)
	  return y;
  }
}
int forTest(int n)
{
  int x = 2;
  int y = 4 * 3;
  println(bla());
  for (y = 3; y < x; y = y + 1)
	println(y);
  for (;;)
  {
	x = x - 1;
	println(x);
	if (x == 0)
	  return 42;
  }
  return 32;
}

int main()
{
    int n;
    n = 6 * 6;
	println(forTest(42));
	println(bla(2345));
}
