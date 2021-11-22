# Demo Project for [Scenario Builder][ScenarioBuilder]

This is a small demo project that shows how you can use the [Scenario Builder Library][ScenarioBuilder]
for Backend and Frontend System Integration tests.

For Backend tests, see [this example](src/test/java/com/example/demo/ScenarioTest.java).

For Frontend tests, it provides a ["Build Scenario" endpoint](src/main/java/com/example/demo/application/controller/ScenarioController.java)
which accepts a Groovy script to use the exact same methods as Backend tests to set up a test scenario.

## Example 

First, start the application.

Then send a request to the "Build Scenario" endpoint:

- POST http://localhost:8080/build-scenario
- Content-Type: text/plain

```groovy
import com.innogames.scenariobuilder.Ref

var aliceRef = namedRef("alice")
var bobRef = new Ref()

scenario
    .withPlayer(player -> player
        .ref(aliceRef)
        .withName("Alice")
    )
    .withPlayer(player -> player
        .ref(bobRef)
        .withName("Bob")
    )
    .withAlliance(alliance -> alliance
        .withName("My Alliance")
        .withMember(aliceRef)
        .withMember(bobRef)
    )
```

Response:

```json
{
    "alice": "b18d3d8b-23ea-4966-91a9-0d5d4d359442"
}
```

Then open http://localhost:8080/data in the browser to verify that the defined entities were created.
It will print something like:

> **Players**<br/>
> Player(id=b18d3d8b-23ea-4966-91a9-0d5d4d359442, name=Alice)<br/>
> Player(id=3311af50-2bc8-47e4-81b6-ef422121aba6, name=Bob)
>
> **Alliances**<br/>
> Alliance(id=295cba9a-d53e-4c51-9ca5-61b5e4322e90, name=My Alliance, memberIds=[b18d3d8b-23ea-4966-91a9-0d5d4d359442, 3311af50-2bc8-47e4-81b6-ef422121aba6])

[ScenarioBuilder]: https://github.com/innogames/junit5-scenario-builder
