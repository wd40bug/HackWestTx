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

export type Hunter = {} //TODO: Fill this in
