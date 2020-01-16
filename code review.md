Backend

Why add the web-application-type=NONE property as default option?

Clean code

Comments should not be the way to introduce code…

AES isnt a good class name...

All endpoints are currently POST, this not a good RESTful services practice

Encrypt endpoint requests all data twice, why is that?

The use of logger is good!

The @Resource annotation is a bad practice, use constructor injection instead

The messages are connected with user (and password), but the spring security configuration would have been cool as well. :(

In case of errors in the services, it would have been better to use a custom exception. In some cases the logger level is still info, it should we warn or error as well like in AES file

The tests were pretty good until the EncrypterDataServiceTest, would have loved to see it working as well

Frontend

Would have been nice to see some front-end framework like ReactJS or Angular.

The encryption is not working. In my setup at least. The getDataJson doesn’t seem to get the form fields.

Why add the web-application-type=NONE property as default option?

Would have been nice, if the UI application was on port 8081 (or etc), this way both the api and ui could run without putting them on manually configured tomcats. Also the api needs the context-path property as well then :)


Overall there aren’t any big let downs, but it can be better :) It can be seen that the author is more comfortable with the back-end than with the front-end.