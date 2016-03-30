Overview:
There are 3 main components for this app.

	1.	Network/API:  This is handled by RequestManager.  Both grabbing the initial JSON data and the subsequent image fetches are routed through here.
		⁃	Uses Volley library.  I chose this library because it’s simple to use and it’s quite fast.  It’s very conceivable I could have used the library without this wrapper, but I wanted to organize the networking code into one place.
	2.	Persistance/SQL:  This is handled by the UserModel, User, and Profile objects.  The User and Profile objects save themselves to the disk, whereas the UserModel consolidates where to grab the entire list, as well as managing the state of that list—grabbing from memory and receiving the input of the networking calls.
		⁃	Uses Sugar ORM library.  I chose this library because it sped up implementation of SQL management.  This was recommended to me by someone I met in an android engineering meetup group.  I will note something odd I found with it is it hasn’t quite achieved the the elegance with 1:1 relational mapping that it has with the rest of its implementation.  This is stated clearly on their website.
	3.	  UI Management.  This is all the activities and fragments.  Most of this is just using standard android hooks and grabbing the data from the data model, though it does also interact with the request manager when grabbing images.  This is arguably the messier part of the code.  Were I to clean up some more, I’d probably make an external adapter class.  I might also throw some more polish into it—just maybe have a friend or two help me out aesthetically to make it look nice.  Though honestly, I’ve always found it easier when someone draws up a screen shot I can just imitate.

Things I loved about the project:

	I loved how it was a complete test of all the major app writing parts.  Network interaction, disk persistence, UI, all the major components were there.  I got a good sense of what I’d be working on—this felt like a genuine facsimile of the sort of challenge I’d see if I worked at slack.

Things I loved about my implementation:

	I managed to keep all but one class under 100 lines, and not terribly difficult to read.  The one class that was over was still under 150, and could easily be abstracted to under 100 by pulling out the adapter.  I consider this to be a win because the bigger a class, the higher the hurtle is to understanding what it does.

	Low mess factor:  I wrote specs on the top of all my non-ui classes.  This helped keep their purpose clear so overreach was minimal.  

	No re-inventing the wheel:  Generally if there was a third party tool to make my life easier, I used it.  Furthermore, I picked the ones easily integrated with a line in the build.gradle file.

Things that could have been better:

	The networking stuff I would have preferred to keep only interacting with the model, which the ui pulled from.  There was a bit of deviation from MVC when I grabbed the application class to grab an imagecacher from the request manager.  This is preferable to making it a singleton and grabbing it directly, but it adds an additional assumption placed on the base application class that hurts maintainability.  For more reading on why this bothers me, I reference http://www.joelonsoftware.com/articles/APIWar.html.  Here you can see Maxis made an invalid assumption about the underlying memory management system in DOS which failed to be true in WINDOWS 3.0, and it hurt the maintainability of Windows.  The analogy being, I’m assuming that getApplication() returns an instance of UserListApplication.  If I ever change that, it’s going to break that code, and it shouldn’t.  That said, given that it’s not shrinkware and no file formats depend on it, it’s a somewhat reversible decision, so I left it as is.

	UI polish:  Aside from looking somewhat default, some aspects of the ui seem like they could be cleaner.  The adapter could have been pulled into its own class as stated above.  There could have been a waiting screen in the case of not having data on file from a previous launch.  Some of the names of the UI elements could have been clearer and maybe more consistent.

	For the life of me, I couldn’t find the user_name in the JSON blob anywhere.  Though that would have been an easy fix.
	Whatever my coding mentor has to say :D

I had fun working on this—I did a lot of infrastructure work, streaming, and low level OS hacking as my re-entry into the coding world.  I haven’t actually written an app since Gree days back in 2014.  It was fun to bang one out!  Thanks for the opportunity.  I’m also using this as fodder for my continual coding education with a friend of mine from MIT.  So however this interview shakes out, I feel a net positive!