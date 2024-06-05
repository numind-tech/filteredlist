Explore a strange behavior of FilteredList
===

Run with `./mvnw clean javafx:run`.

Then click on *Toggle Visibility* or *Add node* to modify the source of the
filteredList. You should see the *Label 1* disappears, replaced by *Label 2* or
a new label appearing.

Also, you can see the result of listeners in the console. When the nodeList
changed, it triggers a filteredList change.

If you click on *run gc*, it will execute `System.gc()`.

On the first case, the two buttons will not work as expected with no switch
between label 1 & 2 nor a new label. Moreover, the filteredList listener will
not be triggered anymore as seen in the console.

It will still works as expected on the second case.

The difference is nodeList & filteredList are defined only in the method for
the first case and are fields of the class in the second case which adds a
strong reference to them.
