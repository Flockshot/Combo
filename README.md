# Combo

![Version](https://img.shields.io/badge/version-1.0-blue) ![API](https://img.shields.io/badge/API-Spigot-orange) ![License](https://img.shields.io/badge/license-MIT-green)

**Combo** is a unique Spigot plugin that adds fighting-game style inputs to Minecraft. It allows players to execute specific sequences of actions (Left Click, Right Click, Shift) to trigger special effects, commands, or abilities.

Instead of remembering complex commands, players can discover "exiting new ways to survive" by mastering physical combinations.

---

## 🚀 Features

* **3-Step Combo System:** Detects sequences like `Left -> Left -> Right` or `Shift + Right -> Left -> Right`.
* **Visual Feedback:** Uses the Action Bar to show the current combo stage (e.g., `L - R - _`).
* **Audio Cues:** Configurable sounds for successful combo steps.
* **Highly Configurable:** Adjust timing windows for inputs to make combos easier or harder.
* **PlaceholderAPI Support:** Fully integrated with PlaceholderAPI for messages and formatting.
* **Extensible Event API:** Developers can listen to combo completion events to create custom abilities.

---

## 📦 Installation & Building

Since this project is open-source, you will need to build the artifact from the source code.

### Prerequisites
* **Java Development Kit (JDK) 8** or higher (depending on your server version).
* **Maven** installed on your system.

### Steps
1.  **Clone the Repository:**

2.  **Compile the Plugin:**
    Run the following Maven command to clean and package the project:
    ```bash
    mvn clean package
    ```

3.  **Install:**
    Copy the generated `.jar` file from the `/target` folder into your server's `/plugins/` directory and restart the server.

---

## 🎮 Usage

The core mechanic revolves on performing **3 actions** in quick succession.

### Legend
* **L**: Left Click (Attack)
* **R**: Right Click (Interact)
* **S**: Shift (Sneak)

### How it works
1.  Perform an action (e.g., Left Click).
2.  You will see `L - _ - _` in your action bar.
3.  Perform the next action within the configurable time window (default 15 ticks).
4.  Complete the sequence (e.g., `L - L - R`) to trigger the effect.

*Note: Shift combos are initiated by holding Sneak while performing the sequence.*

---

## ⚙️ Configuration

The `config.yml` allows you to tweak the timing and feedback of combos.

```yaml
# Time in ticks (20 ticks = 1 second) allowed between actions
waitAfterFirstAction: 15
waitAfterSecondAction: 15

# Interaction Settings
allowPhysical: true # Allow interactions with physical blocks/entities to count
actionRight: "R"    # Display text for Right Click
actionLeft: "L"     # Display text for Left Click

# Visuals
defaultActionBarToggle: true
actionBarFormatFirst: "&a%firstaction% &8- &7_ &8- &7_"
actionBarFormatSecond: "&a%firstaction% &8- &a%secondaction% &8- &7_"
actionBarFormatThird: "&a%firstaction% &8- &a%secondaction% &8- &a%thirdaction%"

# Audio
defaultSoundToggle: true
defaultSound: UI_BUTTON_CLICK
soundVolume: 1
soundPitch: 0
```

---

## 🔗 Developer API

Developers can hook into the Combo system using the provided events.

### Example Listener
```java
import me.flockshot.combo.events.PlayerComboEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ComboListener implements Listener {

    @EventHandler
    public void onCombo(PlayerComboEvent event) {
        // Check the combo type (derived from inputs)
        // Example: LLR (Left, Left, Right)
        if (event.getComboType().toString().equals("LLR")) {
            event.getPlayer().sendMessage("You executed the secret move!");
            // Do custom logic here
        }
    }
}
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
