import type { RequestEvent } from "./$types";

export async function GET({ url } : RequestEvent) 
{
  let data = await fetch("http://localhost:8080/test");
  return data;
}
