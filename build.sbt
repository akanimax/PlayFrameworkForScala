name := "just-play-scala"

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

mappings in Universal ++=
  (baseDirectory.value / "public/images/media" * "*" get) map
    (x => x -> ("public/images/media/" + x.getName))