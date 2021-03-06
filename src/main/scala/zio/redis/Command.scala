package zio.redis

final case class Command[-In, +Out] private[redis] (
  name: String,
  input: Input[In],
  output: Output[Out]
) {

  // main command interpreter
  def run(in: In): Out = ???
}

object Command {
  implicit final class Arg0[+Out](private val command: Command[Unit, Out]) extends AnyVal {
    def apply(): Out = command.run(())
  }

  implicit final class Arg1[-A, +Out](private val command: Command[A, Out]) extends AnyVal {
    def apply(a: A): Out = command.run(a)
  }

  implicit final class Arg1Varargs[-A, -B, +Out](private val command: Command[(A, (B, List[B])), Out]) extends AnyVal {
    def apply(a: A)(b: B, bs: B*): Out = command.run((a, (b, bs.toList)))
  }

  implicit final class Arg2[-A, -B, +Out](private val command: Command[(A, B), Out]) extends AnyVal {
    def apply(a: A, b: B): Out = command.run((a, b))
  }

  implicit final class Arg3[-A, -B, -C, +Out](private val command: Command[(A, B, C), Out]) extends AnyVal {
    def apply(a: A, b: B, c: C): Out = command.run((a, b, c))
  }
}
