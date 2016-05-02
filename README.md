This small app helped my to track down my application at the Canada Visa Application Centre.
I made it to avoid entering my tracking number and DoB again and again on the status page.
It was my hands-on experiment with Clojure.

The sad news is that this app does no longer work because VFS introduced a Captcha. :trollface:

## Usage

Run it on Heroku or call `(vfs/get-visa-status "tracking number" "dob"))` wherever you like.

## License

MIT License

Copyright (c) 2015 Kir Shatrov
