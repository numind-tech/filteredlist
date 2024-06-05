package tech.numind;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FilteredListFails {
    public Node create() {
        // Create an ObservableList of Nodes
        ObservableList<Node> nodeList = FXCollections
                .observableArrayList(node -> new Observable[] { node.visibleProperty() });
        InvalidationListener nodeListListener = obs -> System.out
                .println("nodeList changed: " + System.currentTimeMillis());
        nodeList.addListener(nodeListListener);

        // Create a FilteredList based on the visibility of nodes
        var filteredList = nodeList.filtered(Node::isVisible);
        InvalidationListener filteredListListener = obs -> System.out
                .println("filteredList changed: " + System.currentTimeMillis());
        filteredList.addListener(filteredListListener);

        // Create a VBox to display the filtered nodes
        var vbox = new VBox();
        vbox.setSpacing(5);
        Bindings.bindContent(vbox.getChildren(), filteredList);

        // Add the nodes to the VBox
        var label1 = new Label("Label 1");
        var label2 = new Label("Label 2, now visible!");
        label2.setVisible(false);
        var label3 = new Label("Label 3, always visible");
        nodeList.addAll(label1, label2, label3);

        // Toggle visibility of nodes to test the filtered list
        var toggleVisibilityButton = new Button("Toggle Visibility");
        toggleVisibilityButton.setOnAction(event -> {
            label1.setVisible(!label1.isVisible());
            label2.setVisible(!label2.isVisible());
        });
        // A button to add new labels
        var add = new Button("add label");
        add.setOnAction(event -> nodeList.add(new Label("new label")));

        var container = new VBox(vbox, toggleVisibilityButton, add);
        container.setSpacing(5);
        return container;
    }
}
