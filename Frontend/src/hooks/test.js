import { create } from 'zustand'

export const testCount = create(set => ({
    count: 0,
    setCount: () => { count = count + 1 }
}))