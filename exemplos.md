

# Examples
Use these examples as inputs to our Applet:

```
# Ok
let fun int|string postProcess int|string x =
	let fun int mult int x int y =
		if (x == 0) then (0)
		else (y + (mult((x-1),y)))
	in
		if typeof x == "string"
			then x ++ "10"
		else mult(x, 100) + 10
in postProcess(5)
```

```
# Ok
let fun int|string formatData int|string input =
if typeof input == "string"
then
"Text: " ++ input
else
input
in
formatData("hello")
```

```
# Ok
let int|string value = 42 in value
```

```
# Ok
let int|string value = "hello" in value
```

```
# Ok
let int|string x = 2, boolean|int y = 3 in x+y
```

```
# Ok
let fun int|boolean identity int|boolean x = x in
    identity(true)

# Erro de tipos
let fun int|boolean identity int|boolean x = x in
    identity("true")
```

```
# Ok
let int|string x = 2 in
	let int|string choice = 
    	if (typeof x == "string") 
    		then 100 
    	else "alternative" 
in choice
```



