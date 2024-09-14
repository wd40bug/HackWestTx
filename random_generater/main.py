import random
import json

# Data lists
clients_list = [
    "Sheriff Buckaroo",
    "Alameda Slim",
    "Miss Dolly Dynamite",
    "Outlaw Slim Jim",
    "Mayor Tumbleweed",
    "Doc Calamity Jane",
    "Rancher Rusty Nails",
    "Prospector Gold Rush",
    "Saloon Sally",
    "Deputy Dusty Trails",
    "Bandit Bill McGraw"
]

crimes_list = [
    "Stealing the saloon's spittoon",
    "Cow tipping under the moonlight",
    "Cheating at poker with marked cards",
    "Rustling the sheriff's prized chickens",
    "Hitching the town's horses to the wrong wagons",
    "Illegally selling moonshine on Main Street",
    "Burglarizing the local lasso shop",
    "Swapping the town's wanted posters for joke ones",
    "Defacing the town's 'No Spitting' signs",
    "Faking a ghost sighting to scare off the cattle",
    "Swiping the town's favorite rocking chair",
    "Using a fake mustache to rob the general store",
    "Switching the town's water troughs with pickle barrels",
    "Filing false claims for gold nuggets at the assay office",
    "Sneaking into the town's ice house for midnight snacks"
]

first_names = [
    "Buckaroo", "Dolly", "Slim", "Tumbleweed", "Calamity", 
    "Rusty", "Goldie", "Sally", "Dusty", "Bandit",
    "Jed", "Clara", "Jesse", "Rosa", "Hank",
    "Maggie", "Eli", "Nellie", "Roy", "Lottie"
]

last_names = [
    "McGraw", "Ranger", "O'Sullivan", "Hawkins", "Bell", 
    "Gamble", "Carter", "Boone", "Reed", "Fletcher",
    "Tucker", "Wyatt", "West", "Rivers", "Harrison",
    "Bennett", "Crawford", "Stark", "Montgomery", "Ellis"
]

styles_list = [
    "Bank Heist Bonanza", "Saloon Shakedown Shenanigans", "High Noon Hijinks", "Gold Rush Gambits",
    "Horse Heist Hijinks", "Lasso Loophole Tricks", "Campfire Caper Chronicles", "Six-Shooter Snafus",
    "Wanted Poster Pranks", "Barrel Banditry", "Whiskey Swindle", "Bootlegger Boogie",
    "Dusty Getaway Dramas", "Cattle Rustler Rumble", "Outlaw Hide-and-Seek"
]

class_list = ["Bandit", "Rustler", "Murderer", "Hustler", "Vandal"]


def generate_bounty():
    # Generate random name
    name = random.choice(first_names) + ' ' + random.choice(last_names)

    # Picture URL
    pic = "https://picsum.photos/400/400.jpg"

    # Generate random latitude and longitude
    latitude = random.uniform(-90, 90)
    longitude = random.uniform(-180, 180)
    
    # Update last_known to be a dictionary with latitude and longitude
    last_known = {
        "lat": latitude,
        "lon": longitude
    }

    # Generate danger value (0-100)
    danger = round(random.uniform(0, 100), 0)

    # Generate reward related to danger value plus random noise
    reward = (danger / 100) * 1000 + random.uniform(-50, 50)
    reward = round(max(1, min(reward, 1000)), 0)

    # Choose random client and crime
    client = random.choice(clients_list)
    crime = random.choice(class_list)

    # Choose bounty condition
    condition = random.choice(["dead", "alive"])

    # Choose random styles (2-5)
    num_styles = random.randint(2, 5)
    styles = random.sample(styles_list, num_styles)

    # Add new fields: speed, draw-speed, slipperiness, class
    speed = round(random.uniform(0.1, 10.0), 2)  # Speed > 0
    draw_speed = round(random.uniform(1.0, 50.0), 2)  # Draw-speed > 0
    slipperiness = random.randint(0, 100)  # Slipperiness 0-100

    # Create the bounty dictionary
    bounty = {
        "name": name,
        "pic": pic,
        "last_known": last_known,
        "danger": danger,
        "reward": reward,
        "client": client,
        "crime": crime,
        "bounty_condition": condition,
        "styles": styles,
        "speed": speed,
        "draw_speed": draw_speed,
        "slipperiness": slipperiness
    }

    return bounty

def bounty_to_json(bounty):
    return json.dumps(bounty, indent=4)

# Example usage
if __name__ == "__main__":
    bounty = generate_bounty()
    print(bounty_to_json(bounty))