[![Build Status](https://ci.circuitrcay.com/job/PUSH/badge/icon?style=flat-square)](https://ci.circuitrcay.com/job/PUSH/)
# Pretty Unmaintainable SHell (PUSH)
PUSH is a shell, made using Java and the [Graal VM](https://www.graalvm.org), initially as a learning process.

## Building
native-image is currently not supported, due to class loading not being supported in GraalVM.

To build it as a standard .jar, use the command `./gradlew shadowJar` or `gradlew shadowJar` depending on the operating system.

## Installing
Nightly builds are provided via [Jenkins](https://ci.circuitrcay.com/job/PUSH/), for x86_64. aarch64 binaries are still in development.

## Support
The two official support areas are the #push-support channel on the [Discord](https://discord.gg/ssdp3sN) and the `#push-shell` IRC channel on Freenode.

## License
PUSH is released under the Apache 2.0 [license](https://github.com/CircuitCodes/Push/blob/master/LICENSE).
```
   Copyright 2020 CircuitRCAY

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```
