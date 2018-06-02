# fp_complex
# Komplexaufgabe aus FP

Es soll ein Vokabeltrainer programmiert werden.

Das Programm unterstützt die folgenden Use Cases:

1. Trainieren:

Der Vokabeltrainer verfügt über einen Wörterbuch, welches Übersetzungen von Englisch nach Deutsch bzw. Deutsch nach Englisch enthält. Beim Trainieren wird dem Benutzer des Programms zufällig ein Wort dieses Wörterbuches präsentiert. Wünschenswert ist, dass das zu übersetzende Wort prominent präsentiert wird, sodass es für den Benutzer des Programms leicht zu erkennen ist.

Er soll die Möglichkeit haben die Übersetzung des Wortes in ein Textfeld einzugeben. Wird das Wort richtig übersetzt, so erhält er eine entsprechende Meldung und es wird ein anderes, zufällig ausgewähltes Wort des Wörterbuches geladen und dem Benutzer zur Übersetzung präsentiert.

Gelingt es dem Benutzer nicht das Wort richtig zu übersetzen, so hat er solange neue Versuche bis er das Wort richtig übersetzt hat. In beiden Fällen erhält der Benutzer Rückmeldung über den Erfolg oder Misserfolg seines Übersetzungsversuchs.

2. Ändern der Übersetzungsrichtung

Die Default-Übersetzungsrichtung ist von Deutsch nach Englisch. Es soll aber möglich sein die Übersetzungsrichtung zu ändern.Mit dem Ändern der Sprachrichtung wechselt auch die zu übersetzende Sprache beim Use-Case "Trainieren". Das Ändern der Übersetzungsrichtung kann beliebig oft gemacht werden.

3. Wörterbuchpflege

In einem weiteren, separaten Bereich soll es Möglichkeiten geben das vorhandene Wörterbuch zu erweitern. Das geschieht, indem der Benutzer ein Wort und seine Übersetzung in das Wörterbuch einträgt. Ist die Übersetzung bereits vorhanden, soll dem Benutzer ein Fehler präsentiert werden, der diesen darauf hinweist. Sind die Wörter jedoch neu, dann genügt eine kleine Nachricht, welche den Benutzer darüber in Kenntnis setzt, dass das Wort erfolgreich im Wörterbuch aufgenommen wurde.

Zum dauerhaften Speichern des Wörterbuchs soll ein Button vorhanden sein, der alle Wörter die derzeit im Wörterbuch sind persistiert. Beim Start des Programms sollen alle Wörter die in vorherigen Übungen hinzugefügt und gespeichert worden sind geladen werden und sofort zu Beginn zur Verfügung stehen. Auch hier soll der Benutzer über Erfolge und Misserfolge einzelner Operationen in Kenntnis gesetzt werden.

Allgemeines:

Wichtig ist dabei, dass während der gesamten Aufgabe entweder das MVP oder im Falle von JavaFX mit FXML, das MVVM-Muster, als auch das DAO-Muster verwendet wird. Die nachfolgenden Screenshots sollen lediglich als Orientierung dienen, sodass die genaue Gestaltung Ihres eigenen Userinterfaces von diesem Beispiel abweichen kann (seien Sie kreativ!).

Es ist des Weiteren darauf zu achten, dass Textfelder validiert und nach einer erfolgreichen Operation wieder zurückgesetzt werden.

Denken Sie auch daran Unit-Tests für Ihre Modellklassen zu schreiben um sicher zu gehen, dass der Code die gewünschte Funktionalität realisiert. Für jede Methode in den Modellklassen die Geschäftslogik realisieren müssen entsprechende Testmethoden existieren.

Tipp: Starten Sie Ihr Projekt mit den Modellklassen und schreiben Sie parallel Unit-Tests für die Modellklassen. Erst wenn Sie damit zufrieden sind sollten Sie sich um die Oberfläche kümmern.
