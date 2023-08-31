package example

class IO[A](io: => A) {
  def map[B](f: A => B): IO[B] = new IO(f(io))
  def twice: IO[(A, A)] = new IO((io, io))
  def run: A = io
}

object BetterMonadicFor {
  def countTwice(count: => Int): IO[String] =
    for {
      tuple <- new IO(count).twice
      (x, y) = tuple
    } yield s"$x and $y"
}
