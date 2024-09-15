export type Bounty = {
  id: string,
  name: string,
  pic: string,
  last_known: Location,
  reward: number,
  danger: number,
  client: string,
  crime: Crime,
  speed: number,
  draw_speed: number,
  slipperiness: number,
  bounty_condition: Condition,
  styles: string[]
}

export enum HunterClass {
  Gunslinger = "Gunslinger",
  Cowboy = "Cowboy",
  Sniper = "Sniper",
  Trickster = "Trickster",
  Sheriff = "Sheriff",
}

export enum Crime {
  Bandit,
  Rustler,
  Murderer,
  Hustler,
  Vandal
}

export enum Condition {
  dead,
  alive,
  dead_or_alive
}

export type Hunter = {
  id: string,
  name: string,
  pic: string,
  skill: number,
  location: Location,
  speed: number,
  draw_speed: number,
  discernment: number,
  type: HunterClass
}

export type Location = {
  lat: number,
  lon: number,
}

export function Loc2Str(loc: Location) {
  return "( " + loc.lat.toFixed(3) + ", " + loc.lon.toFixed(3) + " )";
}

export function getDefaultHunter(): Hunter {
  return {
    id: "default-id",
    name: "John Doe",
    pic: "https://example.com/default-pic.png",
    skill: 2,
    location: { lat: 0, lon: 0 },
    speed: 5,
    draw_speed: 3,
    discernment: 4,
    type: HunterClass.Cowboy
  };
}
