// you can use this file to prepare a scenario script for the "build scenario" endpoint.
// Thanks to the build-scenario-endpoint.gdsl file you also get autocompletion in IntelliJ!

import com.innogames.scenariobuilder.Ref

var aliceRef = namedRef("alice")
var bobRef = new Ref()

scenario
	.withPlayer(player -> player
		.ref(aliceRef)
		.withName("alice")
	)
	.withPlayer(player -> player
		.ref(bobRef)
		.withName("bob")
	)
	.withAlliance(alliance -> alliance
		.withName("Fireflies")
		.withMember(aliceRef)
		.withMember(bobRef)
	)
