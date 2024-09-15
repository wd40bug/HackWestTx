export type Bounty = {
  id: string,
  name: string,
  pic: string,
  location: [number, number],
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
  Gunslinger,
  Cowboy,
  Sniper,
  Trickster,
  Sheriff
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
  location: [number, number],
  speed: number,
  draw_speed: number,
  discernment: number,
  type: HunterClass
}

export function getDefaultHunter(): Hunter {
  return {
    id: "default-id",
    name: "John Doe",
    pic: "https://example.com/default-pic.png",
    skill: 2,
    location:[0,0],
    speed: 5,
    draw_speed: 3,
    discernment: 4,
    type: HunterClass.Cowboy
  };
}
