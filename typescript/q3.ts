/*
  Define a generic function filterArray<T>(arr: T[], predicate: (item: T) => boolean): T[] that filters an array based on a predicate function.
  Use this function to filter an array of numbers and return only even numbers.
  Use the same function to filter an array of User objects and return users whose email includes "@company.com".

  Export the filterArray function so that the code can be tested in the test file.
*/

export interface User{
  id: number;
  name: string;
  email: string;
}


export function filterArray<T>(
  arr: T[],
  predicate: (item: T)=> boolean
): T[]{
  return arr.filter(predicate);
}
