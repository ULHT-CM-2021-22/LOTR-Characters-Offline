# Characters of LOTR offline

This is an improved version of "Characters of LOTR" that handles offline mode.

It uses the repository pattern and two alternative sources of information:

* LOTRServiceWithRetrofit - connects to https://the-one-api.dev using retrofit
* LOTRDBWithRoom - connects to a local database using room

# Some remarks

* The LOTRRepository is a singleton initialized in the LOTRApplication (i.e., only once on application startup)
This way, LOTRRepository can be used in multiple activities/fragments.